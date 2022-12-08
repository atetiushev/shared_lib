import com.example.model.TestClass

def call(TestClass param) {
    if (param.flag) {
      sh "echo Hello ${param.msg}."
    } else {
      echo "No flag no life"
      echo "param.flag: " + param.flag
      echo "param.msg: " + param.msg
      echo "param.value: " + param.value
    }
}
