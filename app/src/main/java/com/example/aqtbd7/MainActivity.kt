package com.example.aqtbd7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aqtbd7.Adapters.MovieAdapter
import com.example.aqtbd7.Models.Movie
import com.example.aqtbd7.Repository.Repo
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private var popular_movies_page = 1
    private var upcoming_movies_page = 1
    private var toprated_movies_page = 1
    private var searched_movies_page = 1

    private lateinit var popular_movies_recycler_view: RecyclerView
    private lateinit var popular_movies_adapter: MovieAdapter
    private lateinit var popular_movies_layout_manager: LinearLayoutManager

    private lateinit var toprated_movies_recycler_view: RecyclerView
    private lateinit var toprated_movies_adapter: MovieAdapter
    private lateinit var toprated_movies_layout_manager: LinearLayoutManager

    private lateinit var upcoming_movies_recycler_view: RecyclerView
    private lateinit var upcoming_movies_adapter: MovieAdapter
    private lateinit var upcoming_movies_layout_manager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        popular_movies_recycler_view = findViewById(R.id.popular_movies)
        popular_movies_layout_manager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        popular_movies_recycler_view.layoutManager=popular_movies_layout_manager
        popular_movies_adapter = MovieAdapter(mutableListOf(),::startDetails)
        popular_movies_recycler_view.adapter=popular_movies_adapter


        toprated_movies_recycler_view = findViewById(R.id.top_rated_movies)
        toprated_movies_layout_manager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        toprated_movies_recycler_view.layoutManager=toprated_movies_layout_manager
        toprated_movies_adapter = MovieAdapter(mutableListOf(),::startDetails)
        toprated_movies_recycler_view.adapter=toprated_movies_adapter

        upcoming_movies_recycler_view = findViewById(R.id.upcoming_movies)
        upcoming_movies_layout_manager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        upcoming_movies_recycler_view.layoutManager=upcoming_movies_layout_manager
        upcoming_movies_adapter = MovieAdapter(mutableListOf(),::startDetails)
        upcoming_movies_recycler_view.adapter=upcoming_movies_adapter



        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()
    }
    private fun getPopularMovies(){
        Repo.getPopularMovies(popular_movies_page,::popularMoviesFetched,::onError)
    }
    private fun popularMoviesFetched(movies:List<Movie>) {
        popular_movies_adapter.appendMovies(movies)
        attachPopularMoviesOnScrollListener()
    }
    private fun attachPopularMoviesOnScrollListener() {
        popular_movies_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popular_movies_layout_manager.itemCount
                val visibleItemCount = popular_movies_layout_manager.childCount
                val firstVisibleItem = popular_movies_layout_manager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popular_movies_recycler_view.removeOnScrollListener(this)
                    popular_movies_page++
                    getPopularMovies()
                }
            }
        })
    }

    private fun getTopRatedMovies(){
        Repo.getTopRatedMovies(toprated_movies_page,::topRatedMoviesFetched,::onError)
    }
    private fun topRatedMoviesFetched(movies:List<Movie>) {
        toprated_movies_adapter.appendMovies(movies)
        attachTopRatedMoviesOnScrollListener()
    }
    private fun attachTopRatedMoviesOnScrollListener() {
        toprated_movies_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = toprated_movies_layout_manager.itemCount
                val visibleItemCount = toprated_movies_layout_manager.childCount
                val firstVisibleItem = toprated_movies_layout_manager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    toprated_movies_recycler_view.removeOnScrollListener(this)
                    toprated_movies_page++
                    getTopRatedMovies()
                }
            }
        })
    }

    private fun getUpcomingMovies(){
        Repo.getUpcomingMovies(upcoming_movies_page,::upcomingMoviesFetched,::onError)
    }
    private fun upcomingMoviesFetched(movies:List<Movie>) {
        upcoming_movies_adapter.appendMovies(movies)
        attachUpcomingMoviesOnScrollListener()
    }
    private fun attachUpcomingMoviesOnScrollListener() {
        upcoming_movies_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = upcoming_movies_layout_manager.itemCount
                val visibleItemCount = upcoming_movies_layout_manager.childCount
                val firstVisibleItem = upcoming_movies_layout_manager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    upcoming_movies_recycler_view.removeOnScrollListener(this)
                    upcoming_movies_page++
                    getUpcomingMovies()
                }
            }
        })
    }

    private fun onError() {
        Toast.makeText(applicationContext,"Failed to get movies!",Toast.LENGTH_LONG).show()
    }
    private fun startDetails(movie:Movie) {
        val intent = Intent(applicationContext,DetailsActivity::class.java)
        intent.putExtra(MOVIE_ID,movie.id)
        startActivity(intent)
    }

}
