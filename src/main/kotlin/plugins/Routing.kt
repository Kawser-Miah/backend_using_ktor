package com.example.plugins

//import io.ktor.http.ContentType.*
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.resources.Resource
import io.ktor.server.application.*
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.request.receive
import io.ktor.server.request.receiveChannel
import io.ktor.server.request.receiveMultipart
import io.ktor.server.request.receiveStream
import io.ktor.server.request.receiveText
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.resources.*
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.copyAndClose
import io.ktor.utils.io.readRemaining
import io.ktor.utils.io.readText
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileOutputStream
import io.ktor.server.request.receiveNullable
import io.ktor.server.request.receiveParameters


fun Application.configureRouting() {
    install(RoutingRoot){
        route ("/", HttpMethod.Get){
            handle {
                call.respondText ("Hi i am kawser")
            }
        }
    //Video 6
//        routing {
//            route("message1"){
//                install(RequestValidation){
//                    validate<String> {body->
//                        if (body.isBlank()) ValidationResult.Invalid("Body should not be empty")
//                        else if(!body.startsWith("Hello")) ValidationResult.Invalid("Body should start with Hello")
//                        else ValidationResult.Valid
//                    }
//
//                }
//                post {
//                    val message = call.receive<String>()
//                    call.respondText("Received message: $message")
//                }
//            }
//
//            route("message2"){
//                install(RequestValidation){
//                    validate<String> {body->
//                        if (body.isBlank()) ValidationResult.Invalid("Body should not be empty")
//                        else if(!body.startsWith("Hi")) ValidationResult.Invalid("Body should start with Hi")
//                        else ValidationResult.Valid
//                    }
//
//                }
//                post {
//                    val message = call.receive<String>()
//                    call.respondText("Received message: $message")
//                }
//            }
//            post("no") {
//                val message = call.receive<String>()
//                call.respondText("Received message: $message")
//            }
//
//        }
//video 5
//        routing {
//            post("products"){
////                throw Exception("Database failed to initialize")
//
//                call.respond(HttpStatusCode.NotFound)
////                call.respond(HttpStatusCode.BadRequest)
//            }
//
//            post("product"){
//                val product = call.receiveNullable<Product>() ?: return@post call.respond(
//                    HttpStatusCode.BadRequest)
//
//                call.respond(product)
//            }
//
//        }
//video 4
//        routing {
//            //form data
//            post("checkout"){
//                val fromData = call.receiveParameters()
//                val product = fromData["productId"]
//                val quantity = fromData["quantity"]
//
//                call.respondText("Order Confirmed! order id = $product and quantity = $quantity")
//
//            }
//
//            //multipart form data
//            post("multipart"){
//                val data = call.receiveMultipart(formFieldLimit = 1024*1024*1)
//
//                val fields = mutableMapOf<String, MutableList<String>>()
//
//                data.forEachPart { partData->
//                    when(partData){
//                        is PartData.FormItem ->{
//                            val key = partData.name ?: return@forEachPart
//                            fields.getOrPut(key) { mutableListOf()}.add(partData.value)
//
//                            partData.dispose()
//                        }
//                        is PartData.FileItem ->{
//                            val key = partData.name ?: return@forEachPart
//
//                            val fileName = partData.originalFileName ?: return@forEachPart
//                            fields.getOrPut(key) { mutableListOf()}.add(fileName)
//
//                            val file = File("uploads1/$fileName").apply {
//                                parentFile?.mkdirs()
//                            }
//
//                            partData.provider().copyAndClose(file.writeChannel())
//
//                            partData.dispose()
//                        }
//                        else -> {}
//                    }
//                }
//call.respond("Form fields: $fields")
//            }
//        }

        //Video 3
//        routing {
//            post("greet") {
//                val name = call.receiveText()
//                call.respondText("Hello, $name")
//            }
//
//            post("channel"){
//                val channel = call.receiveChannel()
//
//                val text = channel.readRemaining().readText()
//
//                call.respondText (text)
//            }
//
//            post("upload") {
//                val file = File("uploads/sample2.jpg").apply {
//                    parentFile?.mkdirs()
//                }
//
////                val byteArray=call.receive<ByteArray>()
////
////                file.writeBytes(byteArray)
//
////                val stream = call.receiveStream()
////
////                FileOutputStream(file).use {
////                    outputStream->
////                    stream.copyTo(outputStream, bufferSize = 16*1024)
////                }
//
//                val channel = call.receiveChannel()
//
//                channel.copyAndClose(file.writeChannel())
//
//                call.respondText("Succes")
//
//            }
//
//            post("product"){
//                val product = call.receiveNullable<Product>() ?: return@post call.respond(
//                    HttpStatusCode.BadRequest)
//
//                call.respond(product)
//            }
//
//
//        }
        }

    //1
//    routing {
//        get("/") {
//            call.respondText("Hello World!")
//        }
//        account()
//        get("blogs/{id}"){
//            val id = call.pathParameters["id"]
//            val q1 = call.queryParameters["q1"]
//            val q2 = call.queryParameters["q2"]
//
//
//            call.respondText("Hello you can see my $id blogs on coseries and query-1 is $q1, and query-2 is $q2")
//        }
//        get(Regex(".+/test")){
//            call.respondText ("Its test Api")
//        }
//        get(Regex("api/(?<apiVersion>v[1-3])/users")){
//            val version = call.pathParameters["apiVersion"]
//            call.respondText ("It is version $version")
//        }
//        get<Blogs> {
//            blogs->
//            val blogs = blogs.sort
//
//            call.respondText ("Sort order: $blogs")
//        }
//        delete <Blogs.Blog>{
//            blog->
//            val id = blog.id
//            val sort = blog.parent.sort
//            call.respondText ("Id is : $id and sort is:$sort")
//        }
//    }
}

fun Route.account(){
    route("accounts") {
        route("users") {
            get ("{id}"){
                val id = call.pathParameters["id"]

                call.respondText("Hello you can see my $id blogs on coserie!s")


            }
            post("{id}"){}
            patch("{id}") {  }
        }
        route ("auth"){
            post("sign"){}
            post("sign"){}
        }
    }
}
// /blogs/{id}?sort=new
@Resource("blogs")
class Blogs(val sort:String? = "New"){
    @Resource("{id}")
    data class Blog(val parent: Blogs = Blogs(), val id: String)
}

@Serializable
data class Product(
    val name: String,
    val category:String,
    val price:Int,
)
