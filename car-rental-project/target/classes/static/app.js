const base = '/api';

document.getElementById('btnRefresh').addEventListener('click', showAvailable);
document.getElementById('btnCheck').addEventListener('click', checkAvailability);
document.getElementById('btnEstimate').addEventListener('click', estimate);
document.getElementById('btnRent').addEventListener('click', rent);

async function showAvailable() {
  const res = await fetch(`${base}/cars/available`);
  const cars = await res.json();
  const list = document.getElementById('carsList');
  list.innerHTML = cars.map(c => `<div class="car"><strong>ID ${c.id} — ${c.name}</strong> <span class="small">($${c.basePricePerDay}/day)</span></div>`).join('');
}

async function checkAvailability() {
  const id = document.getElementById('checkCarId').value;
  if (!id) return alert('Enter car id');
  const res = await fetch(`${base}/cars/${id}/availability`);
  const div = document.getElementById('checkResult');
  if (res.status === 200) {
    const data = await res.json();
    div.textContent = data.available ? 'Available' : 'Not available';
  } else {
    div.textContent = 'Car not found';
  }
}

//async function estimate() {
//  const id = document.getElementById('estCarId').value;
//  const days = document.getElementById('estDays').value;
//  if (!id || !days) return alert('Enter id and days');
//  const res = await fetch(`${base}/cars/${id}/estimate?days=${days}`);
//  const div = document.getElementById('estResult');
//  if (res.status === 200) {
//    const data = await res.json();
//    div.textContent = `Estimated amount: $${data.amount}`;
//  } else {
//    div.textContent = 'Car not found';
//  }
//}
async function estimate() {
  const id = document.getElementById('estCarId').value;
  const days = document.getElementById('estDays').value;
  if (!id || !days) return alert('Enter id and days');
  const res = await fetch(`${base}/cars/${id}/estimate?days=${days}`);
  const div = document.getElementById('estResult');

  if (res.status === 200) {
    const data = await res.json();
    let msg = `Base price/day: $${data.basePricePerDay}<br>
               Days: ${data.days}<br>
               Final amount: $${data.finalAmount}`;
    if (data.discountPercent > 0)
      msg += `<br><strong>🎉 You got ${data.discountPercent}% discount!</strong>`;
    div.innerHTML = msg;
  } else {
    div.textContent = 'Car not found';
  }
}


async function rent() {
  const id = document.getElementById('rentCarId').value;
  const days = document.getElementById('rentDays').value;
  if (!id || !days) return alert('Enter id and days');

  const res = await fetch(`${base}/rent`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ carId: Number(id), days: Number(days) })
  });

  const div = document.getElementById('rentResult');
  if (res.status === 200) {
    const data = await res.json();
    div.textContent = `${data.message}. Total: $${data.total}`;
    showAvailable(); // refresh list
  } else {
    const text = await res.text();
    div.textContent = `Error: ${text}`;
  }
}
