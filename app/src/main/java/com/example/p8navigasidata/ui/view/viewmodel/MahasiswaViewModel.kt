package com.example.p8navigasidata.ui.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.p8navigasidata.model.Mahasiswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MahasiswaViewModel : ViewModel(){

    private val mahasiswaStateUI = MutableStateFlow(Mahasiswa())
    val mahasiswaUIState: StateFlow<Mahasiswa> = mahasiswaStateUI.asStateFlow()
    fun saveDataMahasiswa(ls: MutableList<String>){
        mahasiswaStateUI.value = Mahasiswa(
            nim = ls[0],
            nama = ls[1],
            email = ls[2]
        )
    }
}