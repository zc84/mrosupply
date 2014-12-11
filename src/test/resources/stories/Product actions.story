Scenario: If item was added to the basketm add to basket should be changed to remove from basket button
Meta:
@tag priority:p2

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And add '1' 'any' items to basket
Then see 'Product added to your cart' message
And delete from basket icon appeared for this item

Scenario: Sort products by price(low to high)
Meta:
@tag priority:p4

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And select 'Price: low to high' from 'Sort by dropdown'
Then products sort 'by price' for '2' pages

Scenario: Sort products by price(high to low)
Meta:
@tag priority:p4

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And select 'Price: high to low' from 'Sort by dropdown'
Then products sort 'by price desc' for '2' pages

Scenario: Products per page limitation
Meta:
@tag priority:p4

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
When select '36' from 'Items per page dropdown'
Then '36' producats available for '2' pages
