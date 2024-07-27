package com.example.newsapp.presentation.navgraph

sealed class Route(val route: String){
    object OnBoardingScreen: Route("onBoardingScreen")
    object HomeScreen: Route("homeScreen")
    object SearchScreen: Route("searchScreen")
    object BookmarksScreen: Route("bookmarksScreen")
    object DetailScreen: Route("detailScreen")
    object AppStarNavigation: Route("appStarNavigation")
    object NewsNavigation: Route("newsNavigation")
    object NewsNavigationScreen: Route("newsNavigationScreen")
}