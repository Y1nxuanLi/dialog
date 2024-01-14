 document.addEventListener("DOMContentLoaded", function () {
    const canvas = document.getElementById("oceanCanvas");
    const ctx = canvas.getContext("2d");

    var fishCounter = localStorage.getItem('fishCounter');
    var storedCreatures = JSON.parse(localStorage.getItem('creatures'));

    if (fishCounter !== null) {
        var fishCount = parseInt(fishCounter, 10); // Convert string to integer
        console.log("Number of fish: " + fishCount);
    } else {
        console.log("Fish counter not found in local storage.");
    }


    // Load background image
    const backgroundImage = new Image();
    backgroundImage.src = 'ocean.png'; // Replace with the path to your background image


    // Decrease the number of creatures every 24 hours
    setInterval(function() {
        if (creatures.length > 0) {
            creatures.shift(); // Removes one creature from the end of the array
        }
    }, 10 * 1000); // 60s
    // Creature constructor
    class Creature {
        constructor(x, y, type) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.speedX = Math.random() * 0.1 + 1; // Random speed
            this.speedY = (type === "crab") ? 0 : Math.random() * 0.5 - 1; // Crabs only move horizontally
            this.image = new Image();
            this.image.src = this.getImagePath(type); // Set image based on creature type
            this.flipped = false; // Track if the image is flipped
        }

        getImagePath(type) {
            switch (type) {
                case "fish":
                    return 'fish1.png';
                case "crab":
                    return 'Crab1.png';
                case "turtle":
                    return 'turtle1.png';
            }
        }


        draw() {
            ctx.save();
            ctx.translate(this.x, this.y);
            if (this.flipped) {
                ctx.scale(-1, 1);
            }
            ctx.drawImage(this.image, -30, -15, 60, 30); // Double the size
            ctx.restore();
        }

        update() {
            this.x += this.speedX;
            if (this.type !== "crab") { // Allow non-crab creatures to move vertically
                this.y += this.speedY;
            }

            // Change direction and flip image when hitting canvas borders
            if (this.x < 0 || this.x > canvas.width) {
                this.speedX *= -1;
                this.flipped = !this.flipped; // Flip the image
            }
            if (this.y < 0 || this.y > canvas.height) {
                this.speedY *= -1;
            }

            // Keep crabs on the bottom
            if (this.type === "crab") {
                this.y = canvas.height - 30;
            }
        }
    }

 
    
    const creatures = []; // Initialize creatures array

   // Load creatures if they exist in localStorage
    if (storedCreatures) {
        for (let creature of storedCreatures) {
            creatures.push(new Creature(creature.x, creature.y, creature.type, creature.speedX, creature.speedY, creature.flipped));
        }
    
    }
    // Function to create a random creature based on rarity
    function createRandomCreature(x, y) {
        const rarity = Math.random();
        let type;

        if (rarity < 0.4) {
            type = "fish";
        } else if (rarity < 0.7) {
            type = "crab";
        } else {
            type = "turtle";
        }

        creatures.push(new Creature(x, y, type));
        saveCreatures();
    }

        function saveCreatures() {
        localStorage.setItem('creatures', JSON.stringify(creatures.map(creature => {
            return {
                x: creature.x,
                y: creature.y,
                type: creature.type,
                speedX: creature.speedX,
                speedY: creature.speedY,
                flipped: creature.flipped
            };
        })));
    }


     fishCounter

    let totalCreatures = Math.floor(fishCounter / 5);
    for (let i = 0; i < totalCreatures; i++) {
        const randomX = Math.random() * canvas.width;
        const randomY = (i % 3 === 0) ? canvas.height - 30 : Math.random() * canvas.height;
        createRandomCreature(randomX, randomY);
    }



    // Attach the click event listener to the canvas
//    canvas.addEventListener("click", onCanvasClick);

    function animate() {
        ctx.drawImage(backgroundImage, 0, 0, canvas.width, canvas.height);

        for (const creature of creatures) {
            creature.update();
            creature.draw();
        }

        requestAnimationFrame(animate); // Request the next frame
    }

    animate();
    function resizeCanvas() {
        var canvas = document.getElementById('oceanCanvas');
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
    }

// Resize the canvas to fill browser window dynamically
    window.addEventListener('resize', resizeCanvas, false);

// Initial call to set the size
    resizeCanvas();
});
