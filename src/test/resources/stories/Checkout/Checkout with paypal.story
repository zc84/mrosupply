Meta:
@capability checkout with paypal

Scenario: 01. Logged user should be able to order items using paypal
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
And click on 'Pay with paypal section'
And click on 'Pay via paypal button'
And fill paypal details
Then see 'Congratulations! You have just completed your order!' message

Scenario: 02. Unlogged user should be able to order items using paypal
Meta:
@tag priority:p1
@issue 329

Given user on the home page
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
And click on 'Pay with paypal section'
And click on 'Pay via paypal button'
And fill paypal details
Then see 'Congratulations! You have just completed your order!' message

Scenario: 03. User should be able to order item from quotes page using paypal
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
And click on 'Pay with paypal section'
And click on 'Pay via paypal button'
And fill paypal details
Then see 'Congratulations! You have just completed your order!' message
