import { apiInstance } from "./index.js";
// import todoStore from "@/store/modules/todoStore.js";

const api = apiInstance();

// 요청 API: 오늘 할일 목록

// {withCredentials: true,}

export async function findAll() {
  return await api.get("/api/todo");
}

export async function save(todo) {
  return await api.post("/api/todo", todo);
}
