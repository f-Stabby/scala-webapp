# Play Scala Todo App

A simple Todo application built with Play Framework, Scala, Slick, and PostgreSQL.

## Features

- Create, read, update, and delete todo items
- PostgreSQL database backend with Slick ORM
- Docker support for easy deployment

## For Python/Django Developers

If you're coming from a Python/Django background, here's a quick mapping of concepts:

| Django/Python                | Play/Scala                     | Notes                                                    |
|-----------------------------|--------------------------------|----------------------------------------------------------|
| Django                      | Play Framework                 | Web framework with similar MVC architecture              |
| Django ORM                  | Slick                         | Database access layer/ORM                                |
| requirements.txt/pip        | build.sbt/sbt                 | Dependency management                                    |
| Django migrations          | Slick evolutions              | Database schema management                              |
| views.py                   | controllers/                  | HTTP request handlers                                   |
| models.py                  | models/                       | Data models                                             |
| Django templates           | Twirl templates               | Server-side templating                                  |
| Django settings.py         | application.conf              | Application configuration                               |
| Django urls.py             | routes                        | URL routing configuration                               |
| Celery                     | Akka                          | Background job processing                               |
| virtualenv                 | sbt shell                     | Development environment                                 |

### Key Differences

1. **Static Typing**: Unlike Python, Scala is statically typed. This means you declare variable types, but Scala's type inference often makes this optional.

2. **Concurrency Model**: While Python uses async/await and Celery for async operations, Play uses Akka for reactive and concurrent programming.

3. **Template Engine**: Instead of Django's template language, Play uses Twirl which is type-safe and integrated with Scala.

4. **Development Workflow**: 
   - Django: `python manage.py runserver`
   - Play: `sbt run` (SBT is both the build tool and development environment)

## Prerequisites

- Docker and Docker Compose
- JDK 17 (for local development)
- SBT (for local development)

## Getting Started

### Running with Docker

The easiest way to run the application is with Docker Compose:

```bash
docker compose up
```

The application will be available at http://localhost:9000/todo

### Development Setup

1. Clone the repository
2. Start the PostgreSQL database:
   ```bash
   docker compose up postgres
   ```
3. Run the application in development mode:
   ```bash
   sbt run
   ```

### Common Development Tasks

| Task                        | Django Command                  | Play/Scala Command                                       |
|----------------------------|--------------------------------|----------------------------------------------------------|
| Start development server   | `python manage.py runserver`   | `sbt run`                                                |
| Open interactive console   | `python manage.py shell`       | `sbt console`                                            |
| Run database migrations    | `python manage.py migrate`     | Automatic with Play evolutions                           |
| Create new migration       | `python manage.py makemigrations` | Create new `.sql` file in `conf/evolutions`             |
| Run tests                  | `python manage.py test`        | `sbt test`                                               |
| Install dependencies       | `pip install -r requirements.txt` | `sbt update`                                            |

## Project Structure

- `app/` - Application source code
  - `controllers/` - HTTP request handlers (similar to Django views)
  - `models/` - Data models (similar to Django models)
  - `views/` - Twirl templates (similar to Django templates)
  - `repositories/` - Database access layer
  - `modules/` - Dependency injection modules (similar to Django apps)
- `conf/` - Configuration files
  - `application.conf` - Main configuration (similar to settings.py)
  - `routes` - URL routes (similar to urls.py)
  - `evolutions/` - Database migrations (similar to Django migrations)

## Learning Resources

- [Play Framework Documentation](https://www.playframework.com/documentation/latest/Home)
- [Slick Documentation](https://scala-slick.org/doc/3.3.0/)