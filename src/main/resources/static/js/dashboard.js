// =============================================
// Quantity Measurement Dashboard
// =============================================

const API_URL = "http://localhost:8080/api/v1/quantities";
let currentType = "LENGTH";

let currentOperation = "compare";

// =============================================
// Units
// =============================================

const units = {

    LENGTH: [

        "FEET",
        "INCHES",
        "YARDS",
        "CENTIMETERS"

    ],

    WEIGHT: [

        "GRAM",
        "KILOGRAM",
        "TONNE"

    ],

    TEMPERATURE: [

        "CELSIUS",
        "FAHRENHEIT"

    ],

    VOLUME: [

        "LITRE",
        "MILLILITRE",
        "GALLON"

    ]

};

// =============================================
// DOM
// =============================================
const addBtn =
document.getElementById("addBtn");

const subtractBtn =
document.getElementById("subtractBtn");

const divideBtn =
document.getElementById("divideBtn");

const compareBtn =
document.getElementById("compareBtn");

const convertBtn =
document.getElementById("convertBtn");
const cards =
document.querySelectorAll(".card");

const buttons =
document.querySelectorAll(".operations button");

const value1 =
document.getElementById("value1");

const value2 =
document.getElementById("value2");

const unit1 =
document.getElementById("unit1");

const unit2 =
document.getElementById("unit2");

const targetUnit =
document.getElementById("targetUnit");
const value2Section =
document.getElementById("value2Container");

const targetSection =
document.getElementById("targetContainer");


const calculateBtn =
document.getElementById("calculateBtn");

const result =
document.getElementById("result");

// =============================================
// Load Units
// =============================================

function loadUnits() {

    let list = units[currentType];

    let dropdowns = [

        unit1,

        unit2,

        targetUnit

    ];

    dropdowns.forEach(drop => {

        drop.innerHTML = "";

        list.forEach(unit => {

            let option =
            document.createElement("option");

            option.value = unit;

            option.textContent = unit;

            drop.appendChild(option);

        });

    });

}

loadUnits();
loadHistory();
updateUI();
// =============================================
// Quantity Type Selection
// =============================================

cards.forEach(card => {

    card.onclick = () => {

        cards.forEach(c => c.classList.remove("active"));

        card.classList.add("active");

        currentType = card.dataset.type;

        loadUnits();

        clearResult();

        updateUI();

    };

});
// =============================================
// Operation Selection
// =============================================

buttons.forEach(button=>{

    button.onclick=()=>{

        buttons.forEach(btn=>{

            btn.style.background="#ECECEC";

            btn.style.color="black";

        });

        button.style.background="#4F8EF7";

        button.style.color="white";

        currentOperation =
        button.textContent.trim().toLowerCase();

       updateUI();

    }

});
// =============================================
// Target Unit
// =============================================

function updateUI() {

    value2Section.style.display = "block";

    targetSection.style.display = "block";

    // Show all buttons by default
    addBtn.style.display = "inline-block";
    subtractBtn.style.display = "inline-block";
    divideBtn.style.display = "inline-block";

    // Temperature
    if (currentType === "TEMPERATURE") {

        addBtn.style.display = "none";
        subtractBtn.style.display = "none";
        divideBtn.style.display = "none";

    }

    switch (currentOperation) {

       case "compare":

           targetSection.style.display = "none";

           break;

        case "convert":

            value2Section.style.display = "none";


            break;

        case "divide":

            targetSection.style.display = "none";

            break;
    }
}
// =============================================
// Loading
// =============================================

function showLoading(){

    calculateBtn.innerHTML="Calculating...";

    calculateBtn.disabled=true;

}

function hideLoading(){

    calculateBtn.innerHTML="Calculate";

    calculateBtn.disabled=false;

}
// =============================================
// Result
// =============================================

function showResult(data){

    result.innerHTML=data;

}

function clearResult(){

    result.innerHTML="Waiting...";

}
// =============================================
// Validation
// =============================================

function validate(){

    if(value1.value===""){

        alert("Enter First Value");

        return false;

    }

    if(currentOperation!=="convert"){

        if(value2.value===""){

            alert("Enter Second Value");

            return false;

        }

    }

    return true;
}

// =============================================
// Calculate
// =============================================

calculateBtn.onclick=()=>{

    if(!validate()){

        return;

    }

    showLoading();

    switch(currentOperation){

        case "compare":

            compareQuantity();

            break;

        case "convert":

            convertQuantity();

            break;

        case "add":

            addQuantity();

            break;

        case "subtract":

            subtractQuantity();

            break;

        case "divide":

            divideQuantity();

            break;

    }

}
// =============================================
// Compare
// =============================================

async function compareQuantity() {

    const request = {

        thisQuantityDTO: {

            value: Number(value1.value),

            unit: unit1.value,

            measurementType: currentType

        },

        thatQuantityDTO: {

            value: Number(value2.value),

            unit: unit2.value,

            measurementType: currentType

        }

    };

    try {

        const token = localStorage.getItem("jwt");

        const response = await fetch(API_URL + "/compare", {

            method: "POST",

            headers: {

                "Content-Type": "application/json",

                "Authorization": "Bearer " + token

            },

            body: JSON.stringify(request)

        });

        const data = await response.json();

        hideLoading();

        if (data.success) {

           if(typeof data.result==="boolean"){

               showResult(

                   data.result

                   ?

                   "✅ Quantities are Equal"

                   :

                   "❌ Quantities are Not Equal"

               );


loadHistory();
           }
           else{

               showResult(data.result);


               loadHistory();

           }

        }

        else {

            alert(data.message);

        }

    }

    catch (error) {

        hideLoading();

        alert("Server Error");

        console.log(error);

    }

}
// =============================================
// Convert
// =============================================

async function convertQuantity() {

    const request = {

        thisQuantityDTO: {
            value: Number(value1.value),
            unit: unit1.value,
            measurementType: currentType
        },

        targetQuantityDTO: {
            value: 0,
            unit: targetUnit.value,
            measurementType: currentType
        }
    };

    try{

        const token = localStorage.getItem("jwt");

        const response = await fetch(API_URL + "/convert",{

            method:"POST",

            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+token
            },

            body:JSON.stringify(request)

        });

        const data = await response.json();

        hideLoading();

        if(data.success){

            showResult(
                data.result.value + " " + data.result.unit
            );

            loadHistory();

        }
        else{

            alert(data.message);

        }

    }catch(error){

        hideLoading();

        alert("Conversion Failed");

        console.log(error);

    }

}
// =============================================
// Add
// =============================================
async function addQuantity() {

    const request = {

        thisQuantityDTO: {
            value: Number(value1.value),
            unit: unit1.value,
            measurementType: currentType
        },

        thatQuantityDTO: {
            value: Number(value2.value),
            unit: unit2.value,
            measurementType: currentType
        },

        targetQuantityDTO: {
            value: 0,
            unit: targetUnit.value,
            measurementType: currentType
        }

    };



    try {

        const token = localStorage.getItem("jwt");

        const response = await fetch(API_URL + "/add/target", {

            method: "POST",

            headers: {

                "Content-Type": "application/json",

                "Authorization": "Bearer " + token

            },

            body: JSON.stringify(request)

        });

        const data = await response.json();

        console.log("Response :", data);

        hideLoading();

        if (data.success) {

            showResult(
                data.result.value + " " + data.result.unit
            );


            loadHistory();

        } else {

            showResult(data.message);




loadHistory();
        }

    }
    catch (error) {

        hideLoading();

        console.error(error);

        showResult(error.message);

    }

}
// =============================================
// Subtract
// =============================================

async function subtractQuantity() {

    const request = {

        thisQuantityDTO: {

            value: Number(value1.value),

            unit: unit1.value,

            measurementType: currentType

        },

        thatQuantityDTO: {

            value: Number(value2.value),

            unit: unit2.value,

            measurementType: currentType

        },

        targetQuantityDTO: {

            value: 0,

            unit: targetUnit.value,

            measurementType: currentType

        }

    };

    try {

        const token = localStorage.getItem("jwt");

        const response = await fetch(API_URL + "/subtract/target", {

            method: "POST",

            headers: {

                "Content-Type": "application/json",

                "Authorization": "Bearer " + token

            },

            body: JSON.stringify(request)

        });

        const data = await response.json();

        hideLoading();

        if (data.success) {

            showResult(
                data.result.value + " " + data.result.unit
            );

            loadHistory();


        }

        else {

            alert(data.message);

        }

    }

    catch (error) {

        hideLoading();

        alert("Subtraction Failed");

    }

}
// =============================================
// Divide
// =============================================

async function divideQuantity() {

    const request = {

        thisQuantityDTO: {

            value: Number(value1.value),

            unit: unit1.value,

            measurementType: currentType

        },

        thatQuantityDTO: {

            value: Number(value2.value),

            unit: unit2.value,

            measurementType: currentType

        }

    };

    try {

        const token = localStorage.getItem("jwt");

        const response = await fetch(API_URL + "/divide/target", {

            method: "POST",

            headers: {

                "Content-Type": "application/json",

                "Authorization": "Bearer " + token

            },

            body: JSON.stringify(request)

        });

        const data = await response.json();

        hideLoading();

        if (data.success) {

            showResult(data.result);


loadHistory();
        }

        else {

            alert(data.message);

        }

    }

    catch (error) {

        hideLoading();

        alert("Division Failed");

    }

}
function loadHistory(){

  const token = localStorage.getItem("jwt");

  fetch(API_URL + "/history",{

      headers:{
          "Authorization":"Bearer "+token
      }

  })

        .then(response => response.json())

        .then(data => {

            const tbody =
            document.querySelector("#historyTable tbody");

            tbody.innerHTML = "";

            data.forEach(item=>{

                tbody.innerHTML += `

                <tr>

                    <td>${item.createdAt}</td>

                    <td>${item.thisQuantity.measurementType}</td>

                    <td>${item.operation}</td>

                    <td>${item.result}</td>

                </tr>

                `;

            });

        });

}
// =====================
// Dark Mode
// =====================

const themeBtn =
document.getElementById("themeBtn");

if(localStorage.getItem("theme")==="dark"){

    document.body.classList.add("dark");

    themeBtn.innerHTML="☀️ Light Mode";

}

themeBtn.onclick=()=>{

    document.body.classList.toggle("dark");

    if(document.body.classList.contains("dark")){

        localStorage.setItem("theme","dark");

        themeBtn.innerHTML="☀️ Light Mode";

    }
    else{

        localStorage.setItem("theme","light");

        themeBtn.innerHTML="🌙 Dark Mode";

    }

};
// =====================
// Logout
// =====================

const logoutBtn =
document.getElementById("logoutBtn");


logoutBtn.onclick = () => {

    localStorage.removeItem("jwt");

    window.location.href="/logout";

};