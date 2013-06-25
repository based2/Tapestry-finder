This is a Tapestry 5 web application intended to make it easier to find
Tapestry-compatible components, mixins, pages and modules. Its database
includes entries not only for the components that come with Tapestry, but
also those components (and mixins, pages, etc) that are available in
third-party modules, demo applications like JumpStart, blog posts and
mailing lists.

This app has a look and feel matching the current Tapestry documentation
at https://tapestry.apache.org, and the hope is that it will eventually
be running under that URL and be seamlessly integrated into the official
Tapestry documentation.

See the screenshots in the [wiki] (https://github.com/bobharner/Tapestry-finder/wiki)

Goals
=====

* Help people find Tapestry-compatible components.

* Serve as a good example of how a polished and complete Tapestry 5
application should be designed and constructed. In that sense it is intentionally
over-engineered in some areas. We go to some length to minimize the use of the
HTTP session, for example, despite the fact that the application is unlikely to
need clustering with session replication.

* Serve as examples of how to integrate other Apache products with Tapestry,
including Cayenne, Derby and Shiro.

Contributions
=============

Contributions and suggestions are welcome. You can join the project too, but
because of the hope that this app will one day be an integrated part of the
Tapestry web site, project members will need to have submitted a signed Apache
[Contributor License Agreements] (http://www.apache.org/licenses/#clas) before
joining.

Start
=====

mvn jetty:run
http://localhost:8080/components/
