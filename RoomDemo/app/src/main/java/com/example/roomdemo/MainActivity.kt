package com.example.roomdemo

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdemo.data.Product
import com.example.roomdemo.ui.theme.RoomDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val viewModel: MainViewModel = viewModel(it, "MainViewModel",
                            MainViewModelFactory(LocalContext.current.applicationContext as Application)
                        )
                        ScreenSetup(viewModel)
                    }
                }
            }
        }



    }


}

@Composable
fun ScreenSetup(viewModel: MainViewModel) {

    val allProducts by viewModel.allProducts.observeAsState(listOf())  // LiveData
    val searchResults by viewModel.searchResults.observeAsState(listOf())  // MutableLiveData

    MainScreen(allProducts = allProducts, searchResults = searchResults, viewModel = viewModel)
}

@Composable
fun MainScreen(allProducts: List<Product>,
               searchResults: List<Product>,
               viewModel: MainViewModel) {

    var productName by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var searching by remember { mutableStateOf(false) }
    val onProductTextChange = { text : String ->
        productName = text
    }
    val onQuantityTextChange = { text : String ->
        productQuantity = text
    }

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){
        CustomTextField(
            title = "Product Name",
            textState = productName,
            onTextChange = onProductTextChange,
            keyboardType = KeyboardType.Text
        )
        CustomTextField(
            title = "Quantity",
            textState = productQuantity,
            onTextChange = onQuantityTextChange,
            keyboardType = KeyboardType.Number
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp) ) {
            Button(onClick = {
                if (productQuantity.isNotEmpty()) {
                    viewModel.insertProduct(
                        Product(productName =  productName
                            , quantity = productQuantity.toInt() )
                    )
                    searching = false
                }
            } ) { Text("Add") }

            Button(onClick = {
                searching = true
                viewModel.findProduct(productName)
            }) {   Text("Search") }


            Button(onClick = {
                searching = false
                viewModel.deleteProduct(productName)
            }) {   Text("Delete")}


            Button(onClick = {
                searching = false
                productName = ""
                productQuantity = ""
            })
            { Text("Clear") }
        }
        LazyColumn(
            Modifier.fillMaxWidth().padding(10.dp)
        ) {
            val list = if (searching) searchResults else allProducts
            item {
                TitleRow(head1 = "ID", head2 = "Product", head3 = "Quantity")
            }
            items(list) { product ->
                ProductRow(
                    id = product.id, name = product.productName,
                    quantity = product.quantity
                )
            }
        }
    }
}


@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
    Row(modifier = Modifier
        .background(MaterialTheme.colors.primary)
        .fillMaxWidth()
        .padding(5.dp)
    ) {
        Text(head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f))
        Text(head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f))
        Text(head3, color = Color.White,
            modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun ProductRow(id: Int, name: String, quantity: Int) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
    ) {

        Text(id.toString(), modifier = Modifier
            .weight(0.1f))
        Text(name, modifier = Modifier.weight(0.2f))
        Text(quantity.toString(), modifier = Modifier.weight(0.2f))
    }
}

@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {

    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title)},
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp))
}

@Preview(showBackground = true)
@Composable
fun TitleRowPreview() {
    RoomDemoTheme {
        TitleRow(head1 = "head1", head2 = "head2", head3 = "head3")
    }
}


@Preview(showBackground = true)
@Composable
fun ProductRowPreview() {
    RoomDemoTheme {
        ProductRow(id = 1, name = "name", quantity = 10)
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun CustomTextFliedPreview() {
//    RoomDemoTheme {
//        CustomTextField(title = "title",
//            textState = "textState", onTextChange = {}, keyboardType = KeyboardType.Text
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    RoomDemoTheme {
//        Greeting("Android")
//    }
//}