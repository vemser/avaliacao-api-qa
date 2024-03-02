const puppeteer = require('puppeteer');
const {ImgurClient} = require('imgur');
const fs = require('fs');
const buildNumber = process.argv[2];
const client = new ImgurClient({ clientId: "91cb4ba1e87bbab" }); 
console.log("epa")

async function captureScreenshotAndUpload() {
    console.log("epa1")
    const browser = await puppeteer.launch();
    console.log("epa2")
    const page = await browser.newPage();
    console.log("epa3")

    await page.setViewport({
        width: 1920, 
        height: 1080, 
    });
    console.log("epa4")

    await page.goto('https://rodent-dynamic-dane.ngrok-free.app/login'); 
    await new Promise(resolve => setTimeout(resolve, 15000));
    try {
        await page.click('#root > div > main > div > div > section.mb-4.border.border-gray-300.bg-white.drop-shadow-md > div > footer > button');
    } catch (error) {
        console.log(error)
    }

    await new Promise(resolve => setTimeout(resolve, 5000));

    try {
        await page.type('#j_username', 'admin'); 
        await page.type('#j_password', 'ADMIN'); 
        await page.click('form[name="login"] > button[type="submit"]'); 
    } catch (error) {
        console.log(error)
    }

    await new Promise(resolve => setTimeout(resolve, 5000));
    await page.goto(`https://rodent-dynamic-dane.ngrok-free.app/job/VemSerAPITestsPipeline/${buildNumber}/allure/`);
    await new Promise(resolve => setTimeout(resolve, 5000));

    await page.screenshot({ path: 'screenshot.png' });

    const response = await client.upload({
        image: fs.createReadStream('screenshot.png'), 
        type: 'stream',
      });
      console.log(response.data.link);


    await browser.close();


    return response.data.link;
}


captureScreenshotAndUpload();
