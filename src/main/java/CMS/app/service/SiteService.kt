package CMS.app.service

import CMS.app.entity.Site

interface SiteService {

    fun getSiteData(): Site
    fun changeSiteData(site: Site): Site
}