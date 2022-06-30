package bong.lines.kotlin.basic

import java.io.File
import java.io.InputStream
import java.util.*

fun main() {

    // Kotlin Call
    standardInput_forKotlin();

    // Java Call
    //StandardInput.standardInputTest()
}

private fun standardInput_forKotlin(){
    //standard_readLine()

    //standard_readLine_toInt()

    //standard_multiReadLine()

    standard_readFile()
}

private fun standard_readLine_toInt() {
    val numberInput = readLine()!!.toInt()
    println("입력 받은 내용(Int): $numberInput")
}

private fun standard_readLine() {
    val input = readLine()
    println("입력 받은 내용: $input")
}

private fun standard_multiReadLine(){
    val s = Scanner(System.`in`)
    val a = s.nextInt()
    val n = s.nextInt()
    s.nextLine()
    val line = s.nextLine()
    val arr = line.split(" ")

    for(it in arr){
        if(it.toInt() < n)
            print("$it ")
    }
}

private fun standard_readFile(){
    val path = "/Users/lines/sources/01_bonggit/templates_for_all/10_kotlin/kopringstarter/docs/kotlin/compare/README.md"
    val file = File(path)
    val inputStream: InputStream = file.inputStream()
    val text = inputStream.bufferedReader().use { it.readText() }
    println(text)
}
