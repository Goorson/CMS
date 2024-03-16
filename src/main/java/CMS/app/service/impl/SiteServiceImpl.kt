package CMS.app.service.impl

import CMS.app.entity.Site
import CMS.app.repository.SiteRepository
import CMS.app.service.SiteService
import org.springframework.beans.factory.annotation.Autowired

class SiteServiceImpl(
    @Autowired val siteRepository : SiteRepository
    ): SiteService {

    override fun getSiteData(): Site {
        return siteRepository.findAll()[0]
    }

    override fun changeSiteData(site: Site): Site {
        return siteRepository.save(site)
    }
}