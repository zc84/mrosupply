Scenario: If item was added to the basketm add to basket should be changed to remove from basket button
Meta:
@tag priority:p3

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And add '1' 'any' items to basket
Then see 'Product added to your cart' message
And delete from basket icon appeared for this item