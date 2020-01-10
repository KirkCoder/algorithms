package test

import java.math.BigInteger
import java.util.*

fun findBigPrime(): BigInteger =
    BigInteger.probablePrime(4096, Random())