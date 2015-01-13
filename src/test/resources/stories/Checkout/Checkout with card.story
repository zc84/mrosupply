Meta:
@capability checkout with card

Scenario: 01. Shipment method should be available for the ZKL Bearing Corp items
Meta:
@tag priority:p1
@issue 297

Given user on the home page
And user on the 'ProductsPage' page
When click 'ZKL Bearing Corp.' link
And add '1' 'any' items to basket
When click on 'Basket button'
Then user on the 'ShoppingCartPage'
When click on 'Checkout button'
And fill personal info section
And fill shipping info section
And click on 'Continue button'
Then see 'Shipping Amount' message
And 'Continue button' available

Scenario: 02. Unregistered customer should be able to order an item
Meta:
@tag priority:p1
@issue 329

Given user on the home page
When click 'Brands' link
Then user on the 'ProductsPage'
When click 'ACME Electric' link
And open 'any' item
When click on 'Add to basket button'
Then user on the 'ShoppingCartPage'
When click on 'Checkout button'
And fill personal info section
And fill shipping info section
And click on 'Continue button'
Then see 'Choose shipment method' message
When click on 'Continue button'
And fill credit card details
And click on 'Pay button'
Then see 'Congratulations! You have just completed your order!' message

Scenario: 03. Registered customer should be able to order an item
Meta:
@tag priority:p1
@issue 329

Given logged user on the home page
When click 'Brands' link
Then user on the 'ProductsPage'
When click 'ACME Electric' link
And add '1' 'any' items to basket
And click on 'Basket button'
Then user on the 'ShoppingCartPage'
When click on 'Checkout button'
And fill personal info section
And fill shipping info section
And click on 'Continue button'
Then see 'Choose shipment method' message
When click on 'Continue button'
And fill credit card details
And click on 'Pay button'
And wait while loading
Then see 'Congratulations! You have just completed your order!' message

Scenario: 04. User should be able to order item from quotes page using card
Meta:
@tag priority:p1
@issue 334

Given logged user on the home page
When click 'Brands' link
Then user on the 'ProductsPage'
When click 'ACME Electric' link
And add '1' 'any' items to basket
And click on 'Basket button'
Then user on the 'ShoppingCartPage'
When click 'Save to quote' link
Then user on the 'QuotesPage'
When click on 'Checkout now button'
Then user on the 'ShoppingCartPage'
When click on 'Checkout button'
And fill personal info section
And fill shipping info section
And click on 'Continue button'
Then see 'Choose shipment method' message
When click on 'Continue button'
And fill credit card details
And click on 'Pay button'
And wait while loading
Then see 'Congratulations! You have just completed your order!' message

Scenario: 05. It should be possible to checkout items from each available brand
Meta:
@tag priority:p1
@ignore

Given user on the home page
And user on the 'ProductsPage' page
When click 'linktext' link
And add '1' 'any' items to basket
And click on 'Basket button'
Then user on the 'ShoppingCartPage'
When click on 'Checkout button'
And fill personal info section
And fill shipping info section
And click on 'Continue button'
Then see 'Shipping Amount' message
When click on 'Continue button'
And fill credit card details
And click on 'Pay button'
Then see 'Congratulations! You have just completed your order' message

Examples:
|linktext                   |
|ACME Electric              |
|ACTech-Lenze               |
|AMI Bearings               |
|Baldor Electric Motors     |
|Bando                      |
|Boston Gear                |
|Bussmann                   |
|CARLISLE POWER TRANSMISSION|
|Cementex                   |
|Champion Cutting Tool Corp |
|Climax Metal               |
|Contrinex Sensors          |
|Cooper Bearing             |
|Diamond Chain              |
|Dixon Valve & Coupling     |
|Dodge                      |
|EDT CORP                   |
|Electra Gear/Grove Gear    |
|FK Bearing                 |
|Flexco                     |
|Formsprag                  |
|Fulham Lighting            |
|Gates Rubber               |
|HubCity                    |
|Hyundai                    |
|IDC Select                 |
|IKO                        |
|IPTCI                      |
|Isostatic Industries       |
|Koyo Bearing               |
|Leeson                     |
|Linn Gear                  |
|LittelFuse                 |
|LoveJoy                    |
|MagPowr                    |
|Martin Sprocket            |
|MASKA                      |
|Moline Bearing             |
|MRC Bearing                |
|MROsupply                  |
|Nachi Bearing              |
|Nexen Clutch and Brake     |
|Nord Gear                  |
|PEARL                      |
|Red Lion Controls          |
|SKF                        |
|Smith Bearings             |
|SolaHD                     |
|SPX power team             |
|Sterling Electric          |
|Telco Sensors              |
|Toshiba                    |
|United Abrasives Inc.      |
|Vacon VFD                  |
|Warner Electric            |
|WEG                        |
|WorldWide Electric         |
|ZKL Bearing Corp           |
