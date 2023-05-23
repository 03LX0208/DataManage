<template>
  <AdminHomeView v-if="$store.state.user.identity === 'admin'" />
  <TeacherHomeView v-if="$store.state.user.identity === 'teacher'" />
  <StudentHomeView v-if="$store.state.user.identity === 'student'" />
</template>

<script>
import AdminHomeView from "@/views/Admin/AdminHomeView";
import StudentHomeView from "@/views/Student/StudentHomeView";
import TeacherHomeView from "@/views/Teacher/TeacherHomeView"

export default {
  components: {
    AdminHomeView,
    StudentHomeView,
    TeacherHomeView,
  }
}

</script>