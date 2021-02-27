const { request } = require("http")

const myExpress = require('express')
const myApp = myExpress()


var cars = {"car1" : "BMW" , "car2":"Audi" , "car3":"BENZ"};

var bike_list = [{"bike":"Apache RTR 160 4v BS6"},
{"bike":"Apache RTR 160 4v BS4"},
{"bike":"Apache RTR 180 4v BS6"},
{"bike":"Apache RTR 180 4v BS4"},
{"bike":"Apache RTR 200 4v BS6"},
{"bike":"Apache RTR 200 4v BS4"},
{"bike":"Apache RTR 160 2v BS4"},
{"bike":"Apache RTR 160 2v BS4"},
{"bike":"Apache RR 310"}]

var car = [
    {
      "car":"Lambo"
    },
    {
      "car":"Ferrari"
    },
    {
        "car":"Ferrari"
      },{
        "car":"Ferrari"
      },{
        "car":"Ferrari"
      },{
        "car":"Ferrari"
      },{
        "car":"Ferrari"
      }]

// localhost:9999

myApp.get('/home' , (req , res)=> res.send(cars))

myApp.get('/car' , (req , res)=> res.send(car))

myApp.get('/apache' , (req , res)=> res.send(bike_list))

myApp.get('/product' , (req , res)=> res.send("DELL"))

myApp.listen(9999 , ()=>console.log("learning Site"))


// console.log(typeof bike)
