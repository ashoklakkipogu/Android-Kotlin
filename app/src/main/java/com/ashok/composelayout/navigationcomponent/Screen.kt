package com.ashok.composelayout.navigationcomponent

const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_KEY2 = "name"

const val ROOT_GRAPH_ROUTE = "root"
const val AUTH_GRAPH_ROUTE = "auth"
const val HOME_GRAPH_ROUTE = "home"

sealed class Screen(val route: String) {
    object Home : Screen(route = "Home_Screen")

    /*
    //Mandatory Arguments
    object Detail : Screen(route = "Detail_Screen/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}") {
        fun passId(id: Int): String {
            return "Detail_Screen/$id"
        }
        fun passNameAndId(id: Int, name:String): String {
            return "Detail_Screen/$id/$name"
        }
    }*/
    //Optional Arguments
    object Detail :
        Screen(route = "Detail_Screen?id={$DETAIL_ARGUMENT_KEY}&name={$DETAIL_ARGUMENT_KEY2}") {
        fun passId(id: Int = 0): String {
            return "Detail_Screen?id=$id"
        }

        fun passNameAndId(id: Int = 0, name: String = ""): String {
            return "Detail_Screen?id=$id&name=$name"
        }
    }
    object Login: Screen(route = "login_screen")
    object SignUp: Screen(route = "sign_up_screen")
}