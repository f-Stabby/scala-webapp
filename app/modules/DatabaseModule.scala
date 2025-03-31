package modules

import com.google.inject.{AbstractModule, Provides, Singleton}
import slick.jdbc.JdbcBackend
import play.api.Configuration
import javax.inject.Inject

class DatabaseModule extends AbstractModule {
  @Provides @Singleton
  def providesDatabase(configuration: Configuration): JdbcBackend.Database = {
    JdbcBackend.Database.forConfig("slick.dbs.default.db", configuration.underlying)
  }
} 