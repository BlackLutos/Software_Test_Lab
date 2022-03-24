import time
import requests
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import wait
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service

driver=webdriver.Chrome(service = Service(ChromeDriverManager().install()))
driver.maximize_window()

driver.get("https://www.nycu.edu.tw/" )
original_window = driver.current_window_handle
assert  len(driver.window_handles) == 1

driver.find_element(By.LINK_TEXT, "消息").click()

for window_handle in driver.window_handles:
    if window_handle != original_window:
        driver.switch_to.window(window_handle)
        break
time.sleep(1)

driver.find_element(By.ID,"su-post-3352").click()
#first_news = driver.find_element(By.ID,"su-post-3352")

for window_handle in driver.window_handles:
    if window_handle != original_window:
        driver.switch_to.window(window_handle)
        break
url = driver.current_url
time.sleep(1)
r = requests.get(url)
soup = BeautifulSoup(r.text, "html.parser")
print(soup.h1.text)
main_titles = soup.find_all('p')
for title in main_titles:
    print(title.text)
print(" ")

driver.get("https://www.google.com/search?q=310581027" )
original_window = driver.current_window_handle
assert  len(driver.window_handles) == 1
for window_handle in driver.window_handles:
    if window_handle != original_window:
        driver.switch_to.window(window_handle)
        break
url = driver.current_url
time.sleep(2)
r = requests.get(url)
soup = BeautifulSoup(r.text, "html.parser")
main_titles = soup.find_all('h3')
for title in main_titles:
    if "Heather Lovingood (310581027) on Myspace" in title.text:
        print(title.text)