package com.example.projet_prog_mobile.data.repository

import com.example.projet_prog_mobile.data.api.ApiException
import com.example.projet_prog_mobile.data.api.invoice.InvoiceRemoteDataSource
import com.example.projet_prog_mobile.data.local.product.Product
import com.example.projet_prog_mobile.data.local.product.ProductLocalDataSource
import com.example.projet_prog_mobile.domain.repository.ShopRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val productLocalDataSource: ProductLocalDataSource,
    private val invoiceRemoteDataSource: InvoiceRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : ShopRepository {

    override suspend fun createInvoice(): Boolean {
        return try {
            val invoice = withContext(ioDispatcher) {
                productLocalDataSource.getListProduct()
            }
            invoiceRemoteDataSource.createInvoice(invoice)
            true
        } catch (e: ApiException){
            throw e
        }
    }

    override suspend fun getListProduct(): List<Product> {
        return try {
            withContext(ioDispatcher) {
                productLocalDataSource.getListProduct()
            }
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun addProduct(product: Product) {
        return try {
            withContext(ioDispatcher) {
                productLocalDataSource.addProduct(product)
            }
        } catch (e: Exception){
            throw e
        }
    }

    override suspend fun clearShop() {
        return try {
            withContext(ioDispatcher) {
                productLocalDataSource.clearShop()
            }
        } catch (e: Exception){
            throw e
        }
    }



    override suspend fun deleteProduct(product: Product) {
        return try {
            withContext(ioDispatcher) {
                productLocalDataSource.deleteProduct(product)
            }
        } catch (e: Exception){
            throw e
        }
    }

}