package com.example.bankapp.TransactionRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/* Modul klass, så instansen sparas globalt i @HiltAndroidApp containern
Den annoteras även med singleton mönster så det endast skapas en instans under appens gång.
Använder detta här då klasserna här kommer behövas under hela programmets gång
 */

@Module

/* sidenote: singleton annoteringen sparar instansen i en egen behållare då den
då den ska överleva hela appens gång
 */
@InstallIn(SingletonComponent::class)
abstract class TransactionRepoModule{


    /* Binds annoteringen "binder" samman klasserna så att
    när vi sen ska injecera repo i en annan klass, kommer transactionRepoImpl
    automatiskt "följa med". I samband med @Module, så kommer Hilt kunna "fånga upp"
    denna klass och instanserna
    * */
    @Binds
    abstract fun bindTransactionRepo(
        transactionRepoImpl: TransactionRepoImpl
    ): TransactionRepo


}