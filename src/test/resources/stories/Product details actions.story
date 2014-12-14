Scenario: 01. User should be able to add item to basket from item details page
Meta:
@tag priority:p1


Given logged user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And open 'any' item
Then 'Add to basket button' available
When click on 'Add to basket button'
Then user on the 'ShoppingCartPage'

Scenario: 02. User should be able to add item to favorites from item details page
Meta:
@tag priority:p1

Given logged user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And open 'any' item
Then 'Add to favorite link' available
When click on 'Add to favorite link'
Then see 'Product added to favorite' message

Scenario: 03. User should be able to email item
Meta:
@tag priority:p1
@ignore

Given logged user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And open 'any' item
Then 'Email link' available
When click on 'Email link'
Then see 'Send the product info to' message
When click on 'Send email button'
Then see 'Product info is sent to provided email address' message
And product details email was recieved after '15' mins
