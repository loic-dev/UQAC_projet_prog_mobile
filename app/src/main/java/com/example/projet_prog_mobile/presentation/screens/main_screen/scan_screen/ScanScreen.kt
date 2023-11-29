package com.example.projet_prog_mobile.presentation.screens.main_screen.scan_screen

import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.projet_prog_mobile.presentation.components.CameraDialog
import com.example.projet_prog_mobile.presentation.containers.ScannerContainer
import com.example.projet_prog_mobile.presentation.viewModel.ScannerEvent
import com.example.projet_prog_mobile.presentation.viewModel.ScannerViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScanScreen(
    navController: NavHostController,
    scannerViewModel: ScannerViewModel = hiltViewModel()
) {
    val bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,skipHalfExpanded= true)
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val uiState by scannerViewModel.scannerState.collectAsState()

    val hapticFeedback = LocalHapticFeedback.current
    val activity = remember(context) {
        context as Activity
    }

    BackHandler(enabled = bottomSheetState.isVisible) {
        coroutineScope.launch {
            bottomSheetState.hide()
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                scannerViewModel.onEvent(ScannerEvent.CreatePreviewView(lifecycleOwner))
            } else {
                scannerViewModel.onEvent(ScannerEvent.CameraRequiredDialogVisibility(show = true))
            }
        }
    )

    LaunchedEffect(key1 = bottomSheetState.currentValue) {
        if (uiState.previewView != null && !bottomSheetState.isVisible) {
            scannerViewModel.onEvent(ScannerEvent.BottomSheetHidden)
        }
    }

    LaunchedEffect(key1 = lifecycleOwner) {
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            scannerViewModel.onEvent(ScannerEvent.CreatePreviewView(lifecycleOwner))
        } else {
            permissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    SideEffect {
        if (uiState.showBottomSheet) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            coroutineScope.launch { if (!bottomSheetState.isVisible) bottomSheetState.show() }
            coroutineScope.launch { scannerViewModel.getUpcProduct() }
            scannerViewModel.onEvent(ScannerEvent.BottomSheetShown)

        }
    }

    if (uiState.showCameraRequiredDialog) {
        CameraDialog(
            onContinue = {
                scannerViewModel.onEvent(ScannerEvent.CameraRequiredDialogVisibility(show = false))
                permissionLauncher.launch(android.Manifest.permission.CAMERA)
            },
            onExit = {
                scannerViewModel.onEvent(ScannerEvent.CameraRequiredDialogVisibility(show = false))
                activity.finish()
            }
        )
    }


    ScannerContainer(
        bottomSheetState = bottomSheetState,
        uiState = uiState,
        hideBottomSheet={ coroutineScope.launch { if (bottomSheetState.isVisible) bottomSheetState.hide() }},
        backShoppingPage = {navController.navigateUp()}
    )








}
