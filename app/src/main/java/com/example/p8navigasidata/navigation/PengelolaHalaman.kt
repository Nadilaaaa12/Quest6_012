package com.example.p8navigasidata.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.p8navigasidata.ui.view.screen.MahasiswaFormView
import com.example.p8navigasidata.ui.view.screen.RencanaStudyView
import com.example.p8navigasidata.ui.view.screen.SplashView
import com.example.p8navigasidata.ui.view.viewmodel.MahasiswaViewModel
import com.example.p8navigasidata.ui.view.viewmodel.RencanaStudyViewModel

enum class Halaman {
    Splash,
    Mahasiswa,
    Matakuliah,
    Tampil
}

@Composable
fun PengelolaHalaman(
    modifier: Modifier = Modifier,
    mahasiswaViewModel: MahasiswaViewModel = viewModel(),
    krsViewModel: RencanaStudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val mahasiswaStateUI = mahasiswaViewModel.mahasiswaUIState.collectAsState().value
    NavHost(
        navController = navController,
        startDestination = Halaman.Splash.name,
        modifier = modifier.padding()
    ) {
        composable(route = Halaman.Splash.name) {
            SplashView(
                onMulaiButton = {
                    navController.navigate(Halaman.Mahasiswa.name)
                }
            )
        }
        composable(route = Halaman.Mahasiswa.name) {
            MahasiswaFormView(
                modifier = modifier,
                onSubmitButtonClicked = {
                    mahasiswaViewModel.saveDataMahasiswa(it)
                    navController.navigate(Halaman.Matakuliah.name)
                },
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Halaman.Matakuliah.name) {
            RencanaStudyView(
                mahasiswa = mahasiswaStateUI,
                onSubmitButtonClicked = {
                    krsViewModel.saveDataKRS(it)
                    navController.navigate(Halaman.Tampil.name)
                },
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }



