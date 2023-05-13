<template>
  <NMessageProvider>
    <router-view/>
  </NMessageProvider>
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
import {NMessageProvider} from 'naive-ui'

export default {
  components: {
    NMessageProvider,
  }
}
</script>

<style>

</style>
