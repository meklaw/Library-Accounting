# Цифровой учёт книг
## Задача:
В местной библиотеке хотят перейти на цифровой учет книг. Необходимо реализовать веб-приложение для них. Библиотекари
должны иметь возможность регистрировать читателей, выдавать им
книги и освобождать книги (после того, как читатель возвращает
книгу обратно в библиотеку).

## Сущности:
*Человек* (ФИО, год рождения)  
*Книга* (название, автор, год)

### Отношение между сущностями: Один ко Многим.
У человека может быть множество книг. Книга может принадлежать
только одному человеку.

## Функционал:
1. Страницы добавления, изменения и удаления человека.
2. Страницы добавления, изменения и удаления книги
3. Страница со списком всех людей (люди кликабельные - при клике осуществляется
   переход на страницу человека).
4. Страница со списком всех книг (книги кликабельные - при клике осуществляется
   переход на страницу книги).
5. Страница человека, на которой показаны значения его полей и список взятых книг. Если человек не взял ни одной книги, вместо списка должен быть текст "Человек
   пока не взял ни одной книги".
6. Страница книги, на которой показаны значения полей этой книги и имя человека,
    взявший эту книгу. Если эта книга не была никем взята, то виден текст "Эта
   книга свободна".
7. На странице книги, если книга взята человеком, рядом с его именем есть кнопка
   "Освободить книгу". Эта кнопка нажимается библиотекарем тогда, когда читатель
   возвращает эту книгу обратно в библиотеку. После нажатия на эту кнопку книга снова
   становится свободно и пропадает из списка книг человека.
8. На странице книги, если книга свободна, есть выпадающий список
   со всеми людьми и кнопка "Назначить книгу". Эта кнопка нажимается библиотекарем
   тогда, когда читатель хочет забрать эту книгу домой. После нажатия на эту кнопку, книга 
   принадлежит выбранному человеку и появляется в его списке
   книг.
10. Все поля валидируются - с помощью @Valid и Spring Validator.

## Планы:
[ ] Написать Dockerfile для сборки и запуска приложения.
[ ] Добавить авторизацию с помощью Spring Security.
