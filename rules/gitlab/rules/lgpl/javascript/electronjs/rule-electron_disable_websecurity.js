// License: GNU Lesser General Public License v3.0
// source (original): https://github.com/ajinabraham/njsscan/blob/master/tests/assets/node_source/true_positives/semantic_grep/electronjs/security_electron.js
// hash: e7a0a61
// ruleid:rules_lgpl_javascript_electronjs_rule-electron-disable-websecurity
const mainWindow = new BrowserWindow({
    webPreferences: {
        webSecurity: false
    }
})

// ruleid:rules_lgpl_javascript_electronjs_rule-electron-disable-websecurity
const config = {
    webPreferences: {
        webSecurity: false
    }
}
var newwin = new BrowserWindow(config)


const mainWindow = new BrowserWindow({
    webPreferences: {
        allowRunningInsecureContent: true
    }
})

// ruleid:rules_lgpl_javascript_electronjs_rule-electron-disable-websecurity
var x = new BrowserWindow({
    webPreferences: {
        webSecurity: false,
    }
})


const mainWindow = new BrowserWindow({
    webPreferences: {
        enableBlinkFeatures: 'ExecCommandInJavaScript'
    }
})


const mainWindow = new BrowserWindow({
    webPreferences: {
        allowRunningInsecureContent: true
    }
})


const mainWindow = new BrowserWindow({
    webPreferences: {
        nodeIntegration: true,
        nodeIntegrationInWorker: true
    }
})


const mainWindow = new BrowserWindow({
    webPreferences: {
        contextIsolation: false,
        preload: path.join(app.getAppPath(), 'preload.js')
    }
})

const mainWindow = new BrowserWindow({
    webPreferences: {
        experimentalFeatures: true
    }
})
