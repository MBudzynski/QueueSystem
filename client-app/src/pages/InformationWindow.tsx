export const openInformationPage = () => {

    const newWindow = window.open(
        '',
        'InformationPage',
        'width=400,height=300'
    );

    if (newWindow) {
        newWindow.document.write(`
        <!DOCTYPE html>
        <html lang="pl">
        <head><title>Work station</title>
        <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f0f0;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    
    #content {
      text-align: center;
    }

    #work-station {
      font-size: 36px;
      font-weight: bold;
      margin-bottom: 20px;
      font-size: 110px;
      color: #333;
    }

    #number {
      font-size: 48px;
      font-weight: bold;
      color: green;
      font-size: 100px;
      transition: color 0.5s ease;
    }

    .pulse {
      animation: pulse-animation 1s infinite;
    }

    @keyframes pulse-animation {
      0% {
        transform: scale(1);
        opacity: 1;
      }
      50% {
        transform: scale(1.2);
        opacity: 0.7;
      }
      100% {
        transform: scale(1);
        opacity: 1;
      }
    }
  </style>
        </head>
        <body>
          <div id="content">
            <div id="work-station"></div>
            <div id="number"></div>
          </div>
          <script>
            window.addEventListener("message", (event) => {
              if (event.data.type === "UPDATE_NUMBER") {
               const numberElement = document.getElementById("number");
                numberElement.textContent = event.data.data;
                numberElement.classList.add("pulse");
                setTimeout(() => {
                   numberElement.classList.remove("pulse");
                }, 7000);
              }
              if(event.data.type === "UPDATE_WORK_STATION") {
                  document.getElementById("work-station").textContent = event.data.data;
              }
            });
          </script>
        </body>
        </html>
      `);
    }

    return newWindow;
};