# Веб-приложение для проведения турниров по спортивному варианту игры "Что? Где? Когда?" #

Основной целью данного проекта является создание веб-приложения для проведения турниров по спортивной версии игры "Что? Где? Когда?".

Текущая версия **v0.5** выпущена 26.09.2014.

## Дистрибутивные архивы ##
Дистрибутивные архивы с приложением можно скачать на https://sourceforge.net/projects/chgk/files/.
Инструкцию по запуску приложения читайте тут https://code.google.com/p/chgk/wiki/Install.

## Демо-версии ##
Демо-версия приложения (v0.5) доступна по адресу http://demo-chgk.rhcloud.com. Милости просим.

## Основные возможности и особенности (v0.1) ##
  * Ведение списков турниров и команд, принимающих участие в турнире
  * Редактирование результатов турнира
  * Формирование итоговой таблицы с результатами турнира (ранжирование команд производится по сумме ответов и рейтингу вопросов, на которые команда дала правильный ответ)
  * Приложение поддерживает только две категории команд: младшие школьники, старшие школьники (в следующей версии список категорий будет настраиваемым)

## Основные возможности и особенности (v0.2.1) ##
  * Поддержка настраиваемого списка категорий команд

## Основные возможности и особенности (v0.3) ##
  * Разработана система авторизации
  * Выполнено разделение пользователей приложения на администраторов и организаторов, которые будут иметь доступ к редактированию информации о турнирах, и зрителей (неавторизованных пользователей), которые могут только просматривать информацию о турнирах. Изначально доступны пользователи admin/admin и organizer/organizer.

## Основные возможности и особенности (v0.4) ##
  * Добавлена возможность деления турнира на туры
  * Реализована поддержка сквозной и потуровой нумерации вопросов в турнире
  * Редактирование результатов в разрезе туров
  * Оптимизация скорости работы с базой данных

## Основные возможности и особенности (v0.5) ##
  * Реализована поддержка ранжирования команд по сумме мест в туре при формировании итоговых результатов
  * Добавлена возможность выгрузки результатов турнира в формате PDF

## Направления дальнейшей работы ##
  * Реализация механизма регистрации пользователей и восстановления пароля
  * Ведение состава команд
  * Загрузка состава команд с сайта рейтинга МАК (http://rating.chgk.info/)
  * Выгрузка результатов турнира в формате CSV для импорта на сайт рейтинга МАК (http://rating.chgk.info/)
  * Подготовка бланков карточек для ответов
  * Ввод результатов турнира с помощью USB-сканера штрих-кодов

## Скриншоты ##
<table>
<blockquote><tr>
<blockquote><td width='200px'><i>Стартовая страница</i></td>
<td width='200px'><i>Создание турнира</i></td>
<td width='200px'><i>Список турниров</i></td>
</blockquote></tr>
<tr>
<blockquote><td>
<a href='https://a.fsdn.com/con/app/proj/chgk/screenshots/page1.png'>https://a.fsdn.com/con/app/proj/chgk/screenshots/page1.png/182/137</a>
</td>
<td>
<a href='https://a.fsdn.com/con/app/proj/chgk/screenshots/page2.png'>https://a.fsdn.com/con/app/proj/chgk/screenshots/page2.png/182/137</a>
</td>
<td>
<a href='https://a.fsdn.com/con/app/proj/chgk/screenshots/page3.png'>https://a.fsdn.com/con/app/proj/chgk/screenshots/page3.png/182/137</a>
</td>
</blockquote></tr>
<tr>
<blockquote><td><i>Регистрация команды на турнир</i></td>
<td><i>Список команд</i></td>
<td><i>Редактирование результатов турнира</i></td>
</blockquote></tr>
<tr>
<blockquote><td>
<a href='https://a.fsdn.com/con/app/proj/chgk/screenshots/page4.png'>https://a.fsdn.com/con/app/proj/chgk/screenshots/page4.png/182/137</a>
</td>
<td>
<a href='https://a.fsdn.com/con/app/proj/chgk/screenshots/page5.png'>https://a.fsdn.com/con/app/proj/chgk/screenshots/page5.png/182/137</a>
</td>
<td>
<a href='https://a.fsdn.com/con/app/proj/chgk/screenshots/page6.png'>https://a.fsdn.com/con/app/proj/chgk/screenshots/page6.png/182/137</a>
</td>
</blockquote></tr>
</table></blockquote>

## При разработке мы используем ##

[![](http://www.jetbrains.com/idea/opensource/img/all/banners/idea468x60_white.gif)](http://www.jetbrains.com/idea/)
[![](http://assets.spring.io/drupal/files/header/logo-spring-103x60.png)](http://projects.spring.io/spring-framework/)
[![](https://forum.hibernate.org/styles/hibernate/imageset/site_logo.gif)](http://www.hibernate.org/)
[![](http://upload.wikimedia.org/wikipedia/commons/e/e9/Struts.png)](http://struts.apache.org/)

## Для тестирования мы используем ##

[![](http://ru.selenide.org/images/selenide-logo-big.png)](http://selenide.org/)
[![](http://site.mockito.org/img/mockito-logo-small.svg)](http://mockito.org/)

## Для хостинга демо-приложений мы используем ##
[![](http://upload.wikimedia.org/wikipedia/en/3/3a/OpenShift-LogoType.svg)](http://openshift.com)

## Непрерывная интеграция выполняется силами drone.io ##
[![](https://drone.io/peneksglazami/chgk/status.png)](https://drone.io/peneksglazami/chgk)