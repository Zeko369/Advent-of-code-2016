import java.io.File
import java.util.TreeMap

fun getString(letters: Array<TreeMap<Char, Int>>, asc: Boolean = false): String {
  return letters.joinToString("") { map ->
    (if (asc) {
          map.toList().sortedBy { item -> item.second }
        } else {
          map.toList().sortedByDescending { item -> item.second }
        })[0]
        .first.toString()
  }
}

fun main() {
  val lines = File("./input.txt").readLines()
  val passwordLen = lines[0].length

  val letters = Array(passwordLen) { _ -> TreeMap<Char, Int>() }
  lines.forEach { line ->
    line.forEachIndexed { index, c ->
      if (!letters[index].containsKey(c)) {
        letters[index][c] = 0
      }

      letters[index][c] = letters[index][c]!! + 1
    }
  }

  val x1 = getString(letters, false)
  val x2 = getString(letters, true)

  println(x1)
  println(x2)
}
