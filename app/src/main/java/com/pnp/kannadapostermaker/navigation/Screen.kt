package com.pnp.kannadapostermaker.navigation

sealed class Screen(val route: String) {

    data object Home : Screen("home")

    data object Editor : Screen("editor/{imageUri}")

    companion object {

        fun createEditorRoute(
            imageUri: String
        ): String {
            return "editor/$imageUri"
        }
    }
}