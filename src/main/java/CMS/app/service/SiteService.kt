package CMS.app.service

import CMS.app.entity.Site
import org.springframework.stereotype.Service

@Service
interface SiteService {

    fun getSiteData(): Site
    fun changeSiteData(site: Site): Site
}