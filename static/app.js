// TODO: swap between test and live key
// var stripe = Stripe('pk_test_uLL4Ap35oyPYZ41JZ54IGgDM00mW0EUVve');
var stripe = Stripe('pk_live_na0crbTk9L84pdLcqq5EMtAs00tgSBe5md');

var checkoutButtons = document.querySelectorAll('.checkout-button');

checkoutButtons.forEach(button => {
    var plan = button.dataset.plan
    button.addEventListener('click', function (ev) {
      stripe.redirectToCheckout({
        items: [{
          // Define the product and plan in the Dashboard first, and use the plan
          // ID in your client-side code.
          plan: plan,
          quantity: 1
        }],
        // TODO: template these in dev vs prod
        successUrl: 'https://www.clojuriststogether.org/signup-success',
        cancelUrl: 'https://www.clojuriststogether.org/signup-success'
      })
      .then(function (result) {
        console.error(result)
        if (result.error) {
          // If `redirectToCheckout` fails due to a browser or network
          // error, display the localized error message to your customer.
          var errorDiv = document.createElement('div')
          errorDiv.textContent = result.error.message
          errorDiv.insertBefore(button, null);
        }
      });
    });

});
