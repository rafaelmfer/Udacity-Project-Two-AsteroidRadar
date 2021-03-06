package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main), OnClickAsteroid {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val mainAdapter: MainAdapter = MainAdapter()


    override fun onClickAsteroid(selectedAsteroid: Asteroid) {
        viewModel.onAsteroidClicked(selectedAsteroid)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this@MainFragment
        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        mainAdapter.setListener(this@MainFragment)
        binding.asteroidRecycler.adapter = mainAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observables()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.show_all_week_menu -> {
                viewModel.getAsteroidsFiltered(Filter.WEEK)
                true
            }
            R.id.show_today_menu -> {
                viewModel.getAsteroidsFiltered(Filter.TODAY)
                true
            }
            R.id.show_saved_menu -> {
                viewModel.getAsteroidsFiltered(Filter.WEEK)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observables() {
        viewModel.run {

            asteroids.observe(viewLifecycleOwner, {
                mainAdapter.asteroidList = it
            })

            pictureOfDay.observe(viewLifecycleOwner, {
                handlerImageOfDay(it)
            })

            errorAPI.observe(viewLifecycleOwner, { message ->
                showToast(message)
            })

            errorNoInternetConnection.observe(viewLifecycleOwner, { message ->
                showToast(message)
            })

            navigateToDetailFragment.observe(viewLifecycleOwner, { asteroid ->
                asteroid?.let {
                    findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
                    viewModel.onDetailFragmentNavigated()
                }
            })
        }
    }

    private fun handlerImageOfDay(pictureOfDay: PictureOfDay) {
        if (pictureOfDay.mediaType.equals("image", true)) {
            Picasso.with(context)
                .load(pictureOfDay.url)
                .into(binding.activityMainImageOfTheDay)

            binding.activityMainImageOfTheDay.contentDescription = getString(R.string.nasa_picture_of_day_content_description_format, pictureOfDay.title)
        } else {
            binding.textView.text = getString(R.string.image_not_available)
        }
    }

    private fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
