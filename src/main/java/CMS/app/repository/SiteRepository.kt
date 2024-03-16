package CMS.app.repository

import CMS.app.entity.Site
import org.springframework.data.jpa.repository.JpaRepository

interface SiteRepository : JpaRepository<Site, Long>