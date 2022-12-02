import com.example.TestClass

def call(TestClass param) {
    sh "echo Hello ${param.msg}."
}
