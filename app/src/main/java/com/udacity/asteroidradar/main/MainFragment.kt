package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
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
        return true
    }

    private fun observables() {
        viewModel.run {

            asteroids.observe(viewLifecycleOwner, {
                mainAdapter.asteroidList = it
            })

            pictureOfDay.observe(viewLifecycleOwner, {
                handlerImageOfDay(it)
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

            binding.activityMainImageOfTheDay.contentDescription = pictureOfDay.title
        } else {
            binding.textView.text = getString(R.string.image_not_available)
        }
    }
}
