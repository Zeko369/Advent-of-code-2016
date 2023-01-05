import java.math.BigInteger
import java.security.MessageDigest

fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

fun main() {
  val password1 = StringBuilder()
  val password2 = CharArray(8) { ' ' }

  for(i in 1..1000000000) {
    val hash = md5("reyedfim$i")
    if(hash.startsWith("00000")) {
      if (password1.length < 8) {
        password1.append(hash[5])
      }

      val index = hash[5].toString().toIntOrNull()
      if (index != null && index in 0..7 && password2[index] == ' ') {
        password2[index] = hash[6]
      }

      if (password1.length == 8 && password2.all { it != ' ' }) {
        break
      }
    }
  }

  println(password1)
  println(password2)
}