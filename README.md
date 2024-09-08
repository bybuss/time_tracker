# Time Tracker Application

Это приложение было разработано в рамках кейса "Разработка сервиса для трекинга времени, затраченного на проект" (ООО "РНДСОФТ") на VIII Региональном хакатоне "RinHack". Проект находится на стадии активной разработки.

## Оглавление
- [Описание](#описание)
- [Скриншоты](#скриншоты)
- [Используемые технологии](#используемые-технологии)
- [Установка](#установка)
- [Системные требования](#системные-требования)
- [Контакты](#контакты)
- [Лицензия](#контакты)

## Описание
**Time Tracker Application** — это приложение для трекинга времени, затраченного на созданную задачу в приложении. Само приложение подразумевает работу не только со своими личными задачми, но и отслеживанием затраченного времени роботников в организациях на поставленные задачи, группиорвку задач по группам для удобной сортировки своих планов.

## Скриншоты

### Светлая тема
<p align="center">
  <img src="screenshots/light/main-light.jpg" alt="Главный экран - Светлая тема" width="300" />
  <img src="screenshots/light/create-task-form-light.jpg" alt="Создание задачи - Светлая тема" width="300" />
  <img src="screenshots/light/tasks-list-light.jpg" alt="Список задач - Светлая тема" width="300" />
  <img src="screenshots/light/tasks-list2-light.jpg" alt="Список задач 2 - Светлая тема" width="300" />
  <img src="screenshots/light/profile-light.jpg" alt="Профиль - Светлая тема" width="300" />
</p>

### Темная тема
<p align="center">
  <img src="screenshots/dark/main-dark.jpg" alt="Главный экран - Темная тема" width="300" />
  <img src="screenshots/dark/create-task-form-dark.jpg" alt="Создание задачи - Темная тема" width="300" />
  <img src="screenshots/dark/tasks-list-dark.jpg" alt="Список задач - Темная тема" width="300" />
  <img src="screenshots/dark/tasks-list2-dark.jpg" alt="Список задач 2 - Темная тема" width="300" />
  <img src="screenshots/dark/profile-dark.jpg" alt="Профиль - Темная тема" width="300" />
</p>

## Используемые технологии
| Технология                          | Описание                                                                                          | Статус |
|-------------------------------------|---------------------------------------------------------------------------------------------------|--------|
| **Room**                            | Локальная база данных                                                                             | ⌛      |
| **Apollo (GraphQL)**                | Интеграция с API                                                                                  | ⌛      |
| **OkHttp**                          | Клиент HTTP для сетевых запросов                                                                  | ✅      |
| **Jetpack Compose**                 | Фреймворк для создания пользовательского интерфейса                                               | ✅      |
| **Bottom Navigation Bar**           | Навигация с использованием Safety Compose Navigation                                              | ⌛      |
| **Clean Architecture**              | Архитектурный шаблон                                                                              | ⌛      |
| **MVVM (Model-View-ViewModel)**     | Паттерн проектирования                                                                            | ✅      |
| **UseCases**                        | Реализация бизнес-логики                                                                          | ⌛      |
| **Dagger - Hilt DI**                | Внедрение зависимостей                                                                            | ✅      |
| **Data Store Preferences**          | Хранение access & refresh токенов и времени жизни и добавления access токена                      | ✅      |
| **Тестирование**                    | Автоматическое тестирование                                                                       | ❌      | 
| **WorkManager & ForegroundService** | Управление задачами и фоновыми сервисами                                                          | ❌      |
| **Локализация**                     | Поддержка нескольких языков                                                                       | ❌      |
| **Google Authentication**           | Аутентификация в сервисы Google с помощью [One-Tap](https://github.com/stevdza-san/OneTapCompose) | ✅      |

## Установка
😶‍🌫️ Здесь в дальнейшем будет ссылка на скачивание приложения 😶‍🌫️

## Системные требования
- Android 6.0 (Marshmallow) или выше
- Интернет-соединение для синхронизации данных
- Минимум 2 GB оперативной памяти
   
## Контакты
- Backend Developer: [@jmblx](https://github.com/jmblx)
- Designer: [@ruruchin](https://github.com/ruruchin)
- Mobile Developer: [@bybuss](https://github.com/bybuss)

## Лицензия
😶‍🌫️ Этот проект пока не распространяется ни под какой лицензией, но скоро будет! 😶‍🌫️