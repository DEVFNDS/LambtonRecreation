<!DOCTYPE html>
<html>

<head>
  <title>User Profile</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
    integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous" />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous">
  </script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" />
  <link rel="stylesheet" type="text/css" href="css/userinfo.css" />
  <link rel="icon" type="image/x-icon" href="../assets/images/favicon.ico" />
</head>

<body>
  <nav class="navbar navbar-expand-lg" id="logo">
    <div class="container-fluid">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        </ul>
        <form class="d-flex" role="search">
          <a href="/home/home.html" id="home">Home</a>
        </form>
        <form class="d-flex" role="search">
          <a href="/home/home.html" id="home">Sports</a>
        </form>
        <form class="d-flex" role="search">
          <a href="/home/home.html" id="home">Events</a>
        </form>
        <form class="d-flex" role="search">
          <a href="/home/home.html" id="home">News/Articles</a>
        </form>
        <form class="d-flex" role="search">
          <a href="/login/login.html" id="logout">Logout</a>
        </form>
      </div>
    </div>
  </nav>
  <main>
    <section>
      <h2 class="mt-3 text-center">User Profile</h2>
      <div class="cards">
        <!-- User profile picture -->
        <div class="card-text">
          <img src="images/team-1.jpg" alt="" id="userimg" />
        </div>
        <!-- Card for user information -->
        <div class="card" style="width: 50vh">
          <div class="card-text">
            <h2>John Doe</h2>
            <p class="text-muted">Athlete</p>
            <p class="text-muted">123 Sports Street, City, Country</p>
            <p style="text-decoration: underline">Contact Info:</p>
            <h5>john.doe@example.com</h5>
            <h5>555-123-4567</h5>
          </div>
        </div>
      </div>
    </section>
    <!-- Cards for user Overview and Performance -->
    <section>
      <h2 class="mt-3 text-center">Registered Events</h2>
      <div class="cards">
        <!-- Event 1 -->
        <div class="card" style="width: 50vh">
          <div class="card-text">
            <h2>Marathon Challenge</h2>
            <p class="text-muted"><Strong>Sport ID:</Strong> 001</p>
            <p class="text-muted"><strong>Date and Time:</strong> July 15, 2023, 9:00 AM</p>
            <p class="text-muted"><strong>Location:</strong> City Park Stadium</p>
            <p class="text-muted"><strong>Description:</strong> Join the city marathon and showcase your running skills.</p>
            <p class="text-muted"><strong>Registration Deadline:</strong> July 10, 2023</p>
          </div>
        </div>

        <!-- Event 2 -->
        <div class="card" style="width: 50vh">
          <div class="card-text">
            <h2>Basketball Tournament</h2>
            <p class="text-muted">Sport ID: 002</p>
            <p class="text-muted">Date and Time: August 5, 2023, 2:00 PM</p>
            <p class="text-muted">Location: City Sports Arena</p>
            <p class="text-muted">Description: Compete in our annual basketball tournament and win exciting prizes.</p>
            <p class="text-muted">Registration Deadline: July 30, 2023</p>
          </div>
        </div>
        </div>
    </section>
    <section>
      <h2 class="mt-4 text-center">Performance Metrics</h2>
      <div class="cards">
        <div class="card">
          <div class="card-text">
            <h1>15</h1>
            <p class="text-muted">Hours of Training</p>
          </div>
        </div>
        <div class="card">
          <div class="card-text">
            <h1>4</h1>
            <p class="text-muted">Personal Records Set</p>
          </div>
        </div>
        <div class="card">
          <div class="card-text">
            <h1>9.5</h1>
            <p class="text-muted">Average Rating</p>
            <p class="text-muted">Based on 20 reviews</p>
          </div>
        </div>
        <div class="card">
          <div class="card-text">
            <h1>25</h1>
            <p class="text-muted">Followers</p>
          </div>
        </div>
      </div>
    </section>
    <section>
      <!-- Chips for relevant searches -->
      <div class="container-fluid">
        <h2 class="mt-4 text-center">Favorite Sports</h2>

        <div class="mt-3 chips-row">
          <div class="chip">Running</div>
          <div class="chip">Swimming</div>
          <div class="chip">Basketball</div>
          <div class="chip">Yoga</div>
          <div class="chip">Cycling</div>
        </div>

        <div class="chips-row">
          <div class="chip">Soccer</div>
          <div class="chip">Tennis</div>
          <div class="chip">Gymnastics</div>
        </div>
      </div>
    </section>
  </main>
</body>

</html>
