@RestController
class HelloMessageController {
    @RequestMapping("hello-message")
    def message() {
        "HelloWorld!!"
    }
}
