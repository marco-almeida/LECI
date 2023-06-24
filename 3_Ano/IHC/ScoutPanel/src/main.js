import './app.css'
import App from './App.svelte'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-icons/font/bootstrap-icons.css'
import * as bootstrap from 'bootstrap'

const app = new App({
  target: document.getElementById('app'),
})

export default app
