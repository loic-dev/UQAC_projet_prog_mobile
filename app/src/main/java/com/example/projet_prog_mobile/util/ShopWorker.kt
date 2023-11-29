package com.example.projet_prog_mobile.util

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.projet_prog_mobile.data.local.product.Product
import com.example.projet_prog_mobile.domain.repository.ShopRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.UUID

@HiltWorker
class ShopWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val shopRepository: ShopRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val title = inputData.getString(KEY_TITLE)
        val quantity = inputData.getInt(KEY_QUANTITY, 0)
        val price = inputData.getDouble(KEY_PRICE, 0.0)
        return try {
            if (!title.isNullOrBlank()) {
                shopRepository.addProduct(Product(uid = UUID.randomUUID().toString(),
                    title = title,
                    quantity = quantity,
                    price = price))
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val KEY_TITLE = "title_key"
        const val KEY_QUANTITY = "quantity_key"
        const val KEY_PRICE = "price_key"
    }
}




