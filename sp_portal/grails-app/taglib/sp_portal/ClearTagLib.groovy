class ClearTagLib {
  def clearControl = {

      out << """[${link(action:"clearData", controller:"user"){"Logout"}}]"""

  }
}
