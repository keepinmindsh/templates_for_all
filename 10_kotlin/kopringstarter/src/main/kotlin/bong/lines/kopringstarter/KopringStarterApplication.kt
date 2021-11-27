package bong.lines.kopringstarter

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KopringStarterApplication

fun main(args: Array<String>) {
    runApplication<KopringStarterApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
