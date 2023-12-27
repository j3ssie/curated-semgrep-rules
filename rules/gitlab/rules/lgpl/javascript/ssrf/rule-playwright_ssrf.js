// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/ssrf/ssrf_playwright.js
// hash: e7a0a61
const { chromium } = require('playwright');
const express = require('express')
const app = express()
const port = 3000

app.post('/goto', async (req, res) => {
    const browser = await chromium.launch();
    const page = await browser.newPage();
    let url = 'https://hardcoded.url.com'

    // ruleid:rules_lgpl_javascript_ssrf_rule-playwright-ssrf
    await page.goto(req.foo);

    // ruleid:rules_lgpl_javascript_ssrf_rule-playwright-ssrf
    const newUrl = req.foo.bar;
    await page.goto(newUrl);

    await page.screenshot({ path: 'example.png' });
    await browser.close();
})

app.post('/setContent', async (req, res) => {
    const browser = await chromium.launch();
    const page = await browser.newPage();


    // ruleid:rules_lgpl_javascript_ssrf_rule-playwright-ssrf
    await page.setContent(req.foo['bar']);

    await page.screenshot({ path: 'example.png' });
    await browser.close();
})

app.post('/evaluate', async (req, res) => {

    const browser = await chromium.launch();
    const page = await browser.newPage();


    // ruleid:rules_lgpl_javascript_ssrf_rule-playwright-ssrf
    await page.evaluate(`fetch(${req.foo})`);

    await page.screenshot({ path: 'example.png' });
    await browser.close();
})

app.post('/evaluate', async (req, res) => {

    const browser = await chromium.launch();
    const page = await browser.newPage();


    // ruleid:rules_lgpl_javascript_ssrf_rule-playwright-ssrf
    await page.evaluate(x => fetch(x), req.foo.bar);

    await page.screenshot({ path: 'example.png' });
    await browser.close();
})
