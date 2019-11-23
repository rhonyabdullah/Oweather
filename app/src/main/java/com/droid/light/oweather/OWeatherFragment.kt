package com.droid.light.oweather

import android.Manifest
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.droid.light.BuildConfig
import com.droid.light.R
import com.droid.light.common.base.BaseFragment
import com.droid.light.common.extensions.subscribeSingleLiveEvent
import com.droid.light.databinding.FragmentOweatherBinding
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

/**
 * Project OWeather.
 *
 * Created by Rhony Abdullah Siagian on 2019-11-22.
 */
class OWeatherFragment : BaseFragment<OWeatherViewModel, FragmentOweatherBinding>() {

    @Inject lateinit var bindingModel: OWeatherBindingModel

    private val permissionLocation = BuildConfig.VERSION_CODE
    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context!!)
    }
    private var locationData: LocationData? = null

    override val layoutId: Int get() = R.layout.fragment_oweather

    override fun initComponents() {
        binding.model = bindingModel
        requestLocationUdpates()
        context?.run {
            if (checkLocationGranted()) {
                viewModel.onEventReceived(OWeatherViewModel.Event.RetrieveWeatherData)
            } else requestLocationPerm()
        }
    }

    override fun bindViewModels() {
        subscribeSingleLiveEvent(viewModel.state) { state ->
            when (state) {
                is OWeatherViewModel.State.ShowError -> {
                    Toast.makeText(context!!, state.message, Toast.LENGTH_SHORT).show()
                }
                is OWeatherViewModel.State.OnLoading -> {

                }
                is OWeatherViewModel.State.ShouldRequestLocation -> {
                    if (!checkLocationGranted()) requestLocationPerm()
                    else if (locationData == null) requestLastLocation {
                        viewModel.onEventReceived(OWeatherViewModel.Event.RetrieveWeatherData)
                    }
                }
                is OWeatherViewModel.State.ShowCurrentWeather -> bindingModel.applyWeatherData(state.data)
                is OWeatherViewModel.State.ShowHourlyWeather -> bindingModel.applyHourlyData(state.data)
                is OWeatherViewModel.State.ShowWeeklyWeather -> bindingModel.applyWeeklyData(state.data)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            permissionLocation -> if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                viewModel.onEventReceived(OWeatherViewModel.Event.RetrieveWeatherData)
            } else context?.let {
                Toast.makeText(it, getString(R.string.location_prompt), Toast.LENGTH_SHORT).show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun checkLocationGranted(): Boolean {
        return ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPerm() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), permissionLocation)
    }

    private fun requestLastLocation(onRequestSuccess: (() -> Unit)? = null) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location == null) return@addOnSuccessListener
            locationData = LocationData(location.latitude, location.longitude)
            viewModel.onEventReceived(OWeatherViewModel.Event.OnLocationReceived(locationData!!))
            onRequestSuccess?.invoke()
        }
    }

    private fun requestLocationUdpates() {
        val request = LocationRequest.create().apply {
            interval = 500
            fastestInterval = 100
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            numUpdates = 1
        }
        val callback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                p0?.lastLocation?.let { location ->
                    locationData = LocationData(location.latitude, location.longitude)
                    viewModel.onEventReceived(OWeatherViewModel.Event.OnLocationReceived(locationData!!))
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(request, callback, Looper.getMainLooper())
    }
}
